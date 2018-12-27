export class FavoriteForm {
    userID: number;
    offerID: number;
    
    constructor(userID: number, offerID: number) {
        this.userID = userID;
        this.offerID = offerID;
    }
}