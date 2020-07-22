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
}