import React, { Component } from 'react';

class SidebarComponent extends Component {
    render(){
        return(
            <div className="container">
                <nav className="nav flex-column nav-pills">
                    <a className="nav-link active" aria-current="page" href="/dashboard">Dashboard</a>
                    <a className="nav-link" href="/catalog">Catalog</a>
                </nav>
            </div>
        );
    }
}

export default SidebarComponent;