import { ReservationGroupViewModel } from '../ViewModel/ReservationGroupViewModel';
import { UserGroupViewModel } from './UserGroupViewModel';

export class GroupDetails {
    id: string;
    name: string;
    description: string;
    image: string;
    province: string;
    city: string;
    reservationList: ReservationGroupViewModel[];
    userGroupList: UserGroupViewModel[];

    constructor(){
        this.userGroupList = [];
    }
}
