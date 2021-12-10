import React, { Component } from "react";
import SidebarComponent from "./bodyComponents/sidebarComponent";
import MainCatComponent from "./catalogComponents/maincatComponent";

class CatalogComponent extends Component {
    render(){
        return(
            <div className="container-fluid">
                <div className="row" style={{border: "solid"}}>
                    <div className="col-2" style={{borderRight: "solid"}}>
                        <SidebarComponent/>
                    </div>

                    <div className="col-9">
                        <MainCatComponent/>
                    </div>
                </div>
            </div>
        );
    }
}

export default CatalogComponent;