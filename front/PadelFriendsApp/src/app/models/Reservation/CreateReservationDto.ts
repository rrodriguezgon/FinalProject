export class CreateReservationDto {
    clubId: string;
    amount: number;
    date: string;
    private: boolean;
    status: string;
    users: string[];
    groups: string[];

    constructor(clubId: string, amount: number, date: string, 
                privateParam: boolean, status: string, users: string[], groups: string[]){
            this.clubId = clubId;
            this.amount = amount;
            this.date = date;
            this.private = privateParam;
            this.status = status;
            this.users = users;
            this.groups = groups;
        }
}