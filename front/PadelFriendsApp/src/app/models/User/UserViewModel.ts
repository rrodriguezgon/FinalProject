import { Group } from '../Group/Group';
import { Reservation } from '../Reservation/Reservation';

export class UserViewModel {
    id: string;
    username: string;
    password: string;
    role: string;
    groupList: Group[];
    reservationList: Reservation[];
}
