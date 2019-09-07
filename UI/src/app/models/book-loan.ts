export class BookLoan{
    id: number;
    bookId: number;
    borrowerId: number;
    dateOut: Date;
    dateIn: Date;
    dueDate: Date;

    constructor(borrowerId: number, bookId: number, id?: number, dateOut?: Date, dueDate?: Date){
        this.bookId = bookId;
        this.borrowerId = borrowerId;

        this.id = id;
        this.dateOut = dateOut;
        this.dueDate = dueDate;
    }
}