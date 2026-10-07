import java.util.*;

public class Library {
    Map<String, Branch> branches = new HashMap<>();
    Set<Book> uniqueBooks = new HashSet<>();
    Map<Member, List<Loan>> loansByMember = new HashMap<>();
    List<Loan> allLoans = new ArrayList<>();
    Map<String, PriorityQueue<Reservation>> reservationsByBookId = new HashMap<>();
    Map<Book, Integer> popularityCounter = new HashMap<>();
    List<FineRecord> fineRecords = new ArrayList<>();
    Map<Member, Queue<Notification>> memberNotifications = new HashMap<>();
    Set<Member> blacklistedMembers = new HashSet<>();
    Deque<String> transferHistory = new ArrayDeque<>();
    Map<Member, Integer> activeMembersCounter = new HashMap<>();
    Map<Member, Integer> memberLateCounts = new HashMap<>();

    public boolean borrowBook(Member m, String bookId, String branchId, int currentDay) {
        if (blacklistedMembers.contains(m)) {
            System.out.println("Üzv qara siyahidadir!");
            return false;
        }

        Branch branch = branches.get(branchId);
        List<BookCopy> copies = null;
        if (branch != null) {
            copies = branch.copiesByBookId.get(bookId);
        }

        BookCopy targetCopy = null;
        if (copies != null) {
            for (int i = 0; i < copies.size(); i++) {
                BookCopy copy = copies.get(i);
                if (copy.status == CopyStatus.AVAILABLE) {
                    targetCopy = copy;
                    break;
                }
            }
        }

        if (targetCopy == null) {
            System.out.println("Bu kitabdan yoxdur, rezerv edildi.");
            Book targetBook = null;
            for (Book b : uniqueBooks) {
                if (b.id.equals(bookId)) {
                    targetBook = b;
                    break;
                }
            }

            if (targetBook != null) {
                if (!reservationsByBookId.containsKey(bookId)) {
                    reservationsByBookId.put(bookId, new PriorityQueue<>(Comparator.comparingInt(r -> r.priorityScore)));
                }

                int score = 3;
                if (m.type == MembershipType.PREMIUM) {
                    score = 1;
                } else if (m.type == MembershipType.REGULAR) {
                    score = 2;
                }

                Reservation res = new Reservation(m, targetBook, currentDay, score);
                reservationsByBookId.get(bookId).offer(res);
            }
            return false;
        }

        targetCopy.status = CopyStatus.BORROWED;

        int dueDay;
        if (m.type == MembershipType.PREMIUM) {
            dueDay = currentDay + 21;
        } else {
            dueDay = currentDay + 14;
        }

        String loanId = "L_" + System.currentTimeMillis();
        Loan loan = new Loan(loanId, targetCopy, m, currentDay, dueDay);

        allLoans.add(loan);

        if (!loansByMember.containsKey(m)) {
            loansByMember.put(m, new ArrayList<>());
        }
        loansByMember.get(m).add(loan);

        int popCount = 0;
        if (popularityCounter.containsKey(targetCopy.book)) {
            popCount = popularityCounter.get(targetCopy.book);
        }
        popularityCounter.put(targetCopy.book, popCount + 1);

        int activeCount = 0;
        if (activeMembersCounter.containsKey(m)) {
            activeCount = activeMembersCounter.get(m);
        }
        activeMembersCounter.put(m, activeCount + 1);

        return true;
    }

    public void returnBook(Member m, String loanId, int currentDay) {
        Loan targetLoan = null;
        for (int i = 0; i < allLoans.size(); i++) {
            Loan l = allLoans.get(i);
            if (l.loanId.equals(loanId)) {
                targetLoan = l;
                break;
            }
        }

        if (targetLoan == null) {
            System.out.println("Borc yoxdur.");
            return;
        }

        targetLoan.returned = true;

        if (currentDay > targetLoan.dueDay) {
            int daysLate = currentDay - targetLoan.dueDay;
            double fine = m.calculateFine(daysLate);
            FineRecord fineRecord = new FineRecord(m, fine, "Gecikme", currentDay);
            fineRecords.add(fineRecord);

            int lateCount = 0;
            if (memberLateCounts.containsKey(m)) {
                lateCount = memberLateCounts.get(m);
            }
            lateCount = lateCount + 1;
            memberLateCounts.put(m, lateCount);

            if (lateCount > 1) {
                blacklistedMembers.add(m);
                Notification notif = new Notification(NotificationType.BLACKLIST_WARNING, "Gecikmeniz olduguna gore qara siyahiya salinmisiniz.", currentDay);
                m.receiveNotification(notif);
                memberNotifications.putIfAbsent(m, new LinkedList<>());
                memberNotifications.get(m).offer(notif);
            }
        }

        String bookId = targetLoan.bookCopy.book.id;
        PriorityQueue<Reservation> queue = reservationsByBookId.get(bookId);

        if (queue != null && !queue.isEmpty()) {
            Reservation nextRes = queue.poll();
            targetLoan.bookCopy.status = CopyStatus.BORROWED;

            int dueDay;
            if (nextRes.member.type == MembershipType.PREMIUM) {
                dueDay = currentDay + 21;
            } else {
                dueDay = currentDay + 14;
            }

            Loan newLoan = new Loan("L_" + System.currentTimeMillis(), targetLoan.bookCopy, nextRes.member, currentDay, dueDay);
            allLoans.add(newLoan);

            if (!loansByMember.containsKey(nextRes.member)) {
                loansByMember.put(nextRes.member, new ArrayList<>());
            }
            loansByMember.get(nextRes.member).add(newLoan);

            Notification readyNotif = new Notification(NotificationType.RESERVATION_READY, "Rezerv etdiyiniz kitab hazirdir.", currentDay);
            nextRes.member.receiveNotification(readyNotif);
        } else {
            targetLoan.bookCopy.status = CopyStatus.AVAILABLE;
        }
    }

    public boolean transferBook(String bookId, String fromBranchId, String toBranchId) {
        Branch from = branches.get(fromBranchId);
        Branch to = branches.get(toBranchId);

        List<BookCopy> copies = null;
        if (from != null) {
            copies = from.copiesByBookId.get(bookId);
        }

        BookCopy target = null;
        if (copies != null) {
            for (int i = 0; i < copies.size(); i++) {
                BookCopy c = copies.get(i);
                if (c.status == CopyStatus.AVAILABLE) {
                    target = c;
                    break;
                }
            }
        }

        if (target == null) {
            System.out.println("Transfer ugursuz oldu: Kitab filialda tapilmadi.");
            return false;
        }

        target.status = CopyStatus.IN_TRANSIT;
        copies.remove(target);
        target.branchId = toBranchId;

        if (!to.copiesByBookId.containsKey(bookId)) {
            to.copiesByBookId.put(bookId, new ArrayList<>());
        }
        to.copiesByBookId.get(bookId).add(target);
        target.status = CopyStatus.AVAILABLE;

        transferHistory.addFirst("Kitab " + bookId + ": " + fromBranchId + " -> " + toBranchId);
        if (transferHistory.size() > 5) {
            transferHistory.removeLast();
        }
        return true;
    }

    public List<Book> searchBooks(String keyword) {
        List<Book> result = new ArrayList<>();

        for (Branch b : branches.values()) {
            for (List<BookCopy> copies : b.copiesByBookId.values()) {
                if (copies != null && copies.size() > 0) {
                    Book book = copies.get(0).book;

                    boolean match = false;
                    if (book.title != null && book.title.contains(keyword)) {
                        match = true;
                    }
                    if (book.author != null && book.author.contains(keyword)) {
                        match = true;
                    }
                    if (book.genre != null && book.genre.contains(keyword)) {
                        match = true;
                    }

                    if (match) {
                        if (!result.contains(book)) {
                            result.add(book);
                        }
                    }
                }
            }
        }
        return result;
    }

    public void generateBranchReport(String branchId) {
        Branch b = branches.get(branchId);
        if (b == null) {
            return;
        }

        int totalBooks = 0;
        int activeLoans = 0;
        Set<String> genres = new HashSet<>();

        for (Map.Entry<String, List<BookCopy>> entry : b.copiesByBookId.entrySet()) {
            List<BookCopy> list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                totalBooks++;
            }
            if (list != null && list.size() > 0) {
                genres.add(list.get(0).book.genre);
            }
        }

        for (int i = 0; i < allLoans.size(); i++) {
            Loan l = allLoans.get(i);
            if (l.bookCopy.branchId.equals(branchId)) {
                if (l.returned == false) {
                    activeLoans++;
                }
            }
        }

        System.out.println("Filial: " + b.name + " | Kitab sayi: " + totalBooks + " | Janrlar: " + genres.size() + " | Aktiv Borc sayi: " + activeLoans);
    }

    public List<Member> getTopActiveMembers(int topN) {
        List<Map.Entry<Member, Integer>> list = new ArrayList<>(activeMembersCounter.entrySet());

        list.sort(new Comparator<Map.Entry<Member, Integer>>() {
            @Override
            public int compare(Map.Entry<Member, Integer> o1, Map.Entry<Member, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });

        List<Member> result = new ArrayList<>();
        int count = topN;
        if (count > list.size()) {
            count = list.size();
        }

        for (int i = 0; i < count; i++) {
            result.add(list.get(i).getKey());
        }

        return result;
    }

    public void processNotifications(int currentDay) {
        for (Map.Entry<Member, Queue<Notification>> entry : memberNotifications.entrySet()) {
            Queue<Notification> q = entry.getValue();
            while (!q.isEmpty()) {
                Notification n = q.poll();
                System.out.println("Üzve mesaj: " + n.message);
            }
        }
    }
}