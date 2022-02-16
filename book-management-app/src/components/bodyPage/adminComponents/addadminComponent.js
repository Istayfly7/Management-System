import { Component } from "react";

const required = value => {
    if(!value){
        return (
            <div className="alert alert-danger" role="alert">
                This field is required!
            </div>
        );
    }
};

class AddAdminComponent extends Component {
    render(){
        return(
            <div></div>
        );
    }
}

export default AddAdminComponent;