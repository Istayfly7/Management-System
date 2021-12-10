import React, { Component } from 'react';
import ButtonsComponent from './mainComponents/buttonsComponent';
import CarouselComponent from './mainComponents/carouselComponent';

class MainComponent extends Component {
    render(){
        return(
            <div className="container-fluid">
                <div className="row">
                    <div className="col">
                        <CarouselComponent/>
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <ButtonsComponent/>
                    </div>
                </div>
            </div>
        );
    }
}

export default MainComponent;