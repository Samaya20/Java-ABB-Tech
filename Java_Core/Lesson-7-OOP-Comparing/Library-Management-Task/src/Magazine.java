class Magazine extends Item {
    private int issueNumber;

    public Magazine(String title, int id, int issueNumber) {
        super(title, id);
        this.issueNumber = issueNumber;
    }

    @Override
    public void displayInfo() {
        System.out.println(getBaseInfo() + ", type: magazine, issue: " + issueNumber);
    }
}