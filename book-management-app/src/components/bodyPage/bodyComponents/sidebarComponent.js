import React, { Component } from 'react';

class SidebarComponent extends Component {
    render(){
        return(
            <div className="container-fluid">
                <nav className="nav flex-column">
                    <ul className="nav nav-pills">
                        <li>
                            <a className="nav-link" aria-current="page" href="/dashboard">Dashboard</a>
                        </li>
                        <li>
                            <a className="nav-link" href="/catalog">Catalog</a>
                        </li>
                    </ul>
                </nav>
            </div>
        );
    }
}

export default SidebarComponent;