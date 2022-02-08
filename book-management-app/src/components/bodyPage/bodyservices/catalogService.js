import axios from "axios";

const rootCopyURL = "http://localhost:7071/copies/";

let testUrl = () => {
    try{
        let pobj = axios.get(rootCopyURL);
        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + ": not correct " + (exception));
    }
}

let getCatalog = () => {
    try{
        let pobj = axios.get(rootCopyURL + "catalog");

        /*pobj.then((response) => {
            console.log(JSON.stringify(response.data, null, 3));
        });*/

        return pobj;
    }
    catch(exception){
        console.log(rootCopyURL + "catalog: not correct " + (exception));
    }
}

export {testUrl, getCatalog};