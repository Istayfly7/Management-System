import axios from "axios";

const rootURL = "http://localhost:7071//users/";

let testUrl = () => {
    try{
        let pobj = axios.get(rootURL);
        return pobj;
    }
    catch(exception){
        console.log(rootURL + ": not correct " + (exception));
    }
}

let createUser = (username, password) => {
    try{
        let pobj = axios.post(rootURL + "save", 
        {"type": "VISITOR", "id": 0, "userName": username, "password": password, "bookOnHold": null, "books": null});
        return pobj;
    }
    catch(exception){
        console.log(rootURL + "save : not correct " + (exception));
    }
}

export {testUrl, createUser};