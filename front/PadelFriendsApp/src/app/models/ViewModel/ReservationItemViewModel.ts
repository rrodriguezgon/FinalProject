import { Club } from '../Club/Club';
import { User } from '../User/User';
import { Group } from '../Group/Group';

export class ReservationItemViewModel {
    id: string;
    club: Club;
    date: Date;
    amount: number;
    private: boolean;
    status: string;
    userList: User[];
    groupList: Group[];
}
