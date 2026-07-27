void main() {
    Scanner scanner = new Scanner(System.in);
    Library library = new Library();

    Branch branch = new Branch("b1ac", "merkez filiali", "baki");
    library.branches.put(branch.branchId, branch);

    Member member1 = new Member("m1asd", "Ruslan", MembershipType.PREMIUM, "ruslan@gmail.com");
    Member member2 = new Member("m2afc", "Test", MembershipType.STUDENT, "test@gmail.com");

    Book book1 = new Book("k1dc", "Java", "Ruslan Agakishiyev", "Technolgy", "12345");
    library.uniqueBooks.add(book1);

    BookCopy copy1 = new BookCopy("c2dr", book1, "b1ac", CopyStatus.AVAILABLE);
    branch.copiesByBookId.put("k1dc", new ArrayList<>());
    branch.copiesByBookId.get("k1dc").add(copy1);

    int currentDay = 1;

    while (true) {
        System.out.println("\n--- Library ---");
        System.out.println("1. Kitab Axtar");
        System.out.println("2. Kitab Götür");
        System.out.println("3. Kitab Qaytar");
        System.out.println("4. En Aktiv Memberleri Gör");
        System.out.println("7. Exit");
        System.out.print("Seciminizi daxil edin: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                System.out.print("Axtaris sözünü daxil edin: ");
                String keyword = scanner.nextLine();
                List<Book> searchResults = library.searchBooks(keyword);
                if (searchResults.isEmpty()) {
                    System.out.println("Hec bir kitab tapilmadi.");
                } else {
                    System.out.println("Tapilan kitablar:");
                    for (Book b : searchResults) {
                        System.out.println("id: " + b.id + " | Ad: " + b.title + " | Müellif: " + b.author);
                    }
                }
                break;

            case 2:
                System.out.print("Member adini daxil edin (Ruslan ve ya Test): ");
                String memberName = scanner.nextLine();
                Member selectedMember = memberName.equalsIgnoreCase("Ruslan") ? member1 : member2;

                System.out.print("Kitab id-sini daxil edin (mes: k1dc): ");
                String bId = scanner.nextLine();

                System.out.print("Filial id-sini daxil edin (mes: b1ac): ");
                String fId = scanner.nextLine();

                boolean success = library.borrowBook(selectedMember, bId, fId, currentDay);
                if (success) {
                    System.out.println("Kitab ugurla götürüldü!");
                }
                break;

            case 3:
                System.out.print("Member adini daxil edin (Ruslan ve ya Test): ");
                String mName = scanner.nextLine();
                Member retMember = mName.equalsIgnoreCase("Ruslan") ? member1 : member2;

                String foundLoanId = null;
                for (Loan l : library.allLoans) {
                    if (l.member.equals(retMember) && !l.returned) {
                        foundLoanId = l.loanId;
                        System.out.println("Tapilan aktiv Loan id: " + foundLoanId);
                        break;
                    }
                }

                if (foundLoanId == null) {
                    System.out.println("Bu memberde qaytarilacaq kitabi yoxdur.");
                    break;
                }

                library.returnBook(retMember, foundLoanId, currentDay);
                System.out.println("Kitab ugurla qaytarildi.");
                break;
            case 4:
                System.out.print("Neçe aktiv member gösterilsin?: ");
                int topN = scanner.nextInt();
                List<Member> topMembers = library.getTopActiveMembers(topN);
                System.out.println("Top Aktiv Memberler:");
                for (Member m : topMembers) {
                    System.out.println("- " + m.name);
                }
                break;
            case 7:
                System.out.println("Proqramdan cixilir...");
                scanner.close();
                return;

            default:
                System.out.println("Yanlis secim! Yeniden cehd edin.");
        }
    }
}
