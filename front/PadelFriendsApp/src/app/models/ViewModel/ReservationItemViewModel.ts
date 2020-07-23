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

    constructor(){
        this.id = '';
        this.club = new Club( '', '', '', '', '', 0, 0, 0);
        this.date = new Date();
        this.amount = 0;
        this.private = false;
        this.status = 'CLOSED';
        this.userList = [];
        this.groupList = [];
    }
}
