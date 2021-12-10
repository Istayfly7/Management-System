import React, { Component } from "react";

class ButtonsComponent extends Component {
    render(){
        return(
            <div className="container-fluid">
                <div className="row">
                    <div className="col">
                        <button type="button" className="btn btn-primary">Total Books</button>
                    </div>
                    <div className="col">
                        <button type="button" className="btn btn-info">Total Available</button>
                    </div>
                </div>

                <div className="row">
                    <div className="col">
                        <button type="button" className="btn btn-success">Success</button>
                    </div>
                    <div className="col">
                        <button type="button" className="btn btn-warning">Warning</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default  ButtonsComponent;