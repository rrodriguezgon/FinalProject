import { ReservationItemViewModel } from '../ViewModel/ReservationItemViewModel'

export class Reservation {
    id: string;
    nameClub: string;
    provinceClub: string;
    cityClub: string;
    numJug: number;
    date: Date;
    amount: number;
    isprivate: boolean;

    constructor(id: string, reservationItem: ReservationItemViewModel){
        this.id = id;
        this.nameClub = reservationItem.club.name;
        this.provinceClub = reservationItem.club.province;
        this.cityClub = reservationItem.club.city;
        this.numJug = reservationItem.userList.length;
        this.date = reservationItem.date;
        this.amount = reservationItem.amount;
        this.isprivate = reservationItem.private;
    }
}
