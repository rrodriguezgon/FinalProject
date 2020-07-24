import { UserGroup } from './UserGroup';

export class CreateGroupDto {
    name: string;
    description: string;
    image: string;
    city: string;
    province: string;
    userGroupList: UserGroup[];


    constructor(name: string, description: string, image: string,
                city: string, province: string, userGroupList: UserGroup[]){
                this.name = name;
                this.description = description;
                this.image = image;
                this.city = city;
                this.province = province;
                this.userGroupList = userGroupList;
      }
}
