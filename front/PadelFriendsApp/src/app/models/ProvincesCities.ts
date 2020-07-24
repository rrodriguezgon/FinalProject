export class Province {
    name: string;
    cities: string[];

    constructor(name: string, cities: string[]){
        this.name = name;
        this.cities = cities;
    }
}

export class ProvinceAndCity{
    provinces: Province[];

    constructor(){
        this.provinces = [];

        this.provinces.push(new Province('Madrid', ['Pozuelo de Alarcón', 'Mostoles', 'Colmenarejo', 'Alcorcón']));
        this.provinces.push(new Province('Girona', ['Girona', 'Figueres', 'Cadaqués', 'Roses']));
    }
}
