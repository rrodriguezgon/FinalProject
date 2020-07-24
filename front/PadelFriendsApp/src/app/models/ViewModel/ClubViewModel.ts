import { Reservation } from '../Reservation/Reservation';

export class ClubViewModel{
    id: string;
    name: string;
    ubication: string;
    image: string;
    city: string;
    province: string;
    numberCourts: number;
    latitude: number;
    longitude: number;
    reservationList: Reservation[];

    constructor(){
        this.longitude = -3.707347;
        this.latitude = 40.407353;
    }
}