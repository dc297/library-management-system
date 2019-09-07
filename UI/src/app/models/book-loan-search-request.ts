import { HttpParams } from "@angular/common/http";

export class BookLoanSearchRequest{
    bookId: number;
    borrowerId: number;
    bookName: string;
    borrowerName: string;

    getHttpParams(): HttpParams{
        var httpParams = new HttpParams();
        
        if(this.bookId) httpParams = httpParams.append("bookId", String(this.bookId));
        if(this.borrowerId) httpParams = httpParams.append("borrowerId", String(this.borrowerId));
        if(this.bookName) httpParams = httpParams.append("bookName", this.bookName);
        if(this.borrowerName) httpParams = httpParams.append("borrowerName", this.borrowerName);

        return httpParams;
    }
}