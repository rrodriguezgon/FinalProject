export class Club{
    id: string;
    name: string;
    ubication: string;
    image: string;
    city: string;
    province: string;
    numberCourts: number;
    latitude: number;
    longitude: number;

    constructor(name: string, ubication: string, image: string, city: string, province,
                numberCourts: number, latitude: number, longitude: number){
        this.name = name;
        this.ubication = ubication;
        this.image = image;
        this.city = city;
        this.province = province;
        this.latitude = latitude;
        this.numberCourts = numberCourts;
        this.longitude = longitude;
    }
}
