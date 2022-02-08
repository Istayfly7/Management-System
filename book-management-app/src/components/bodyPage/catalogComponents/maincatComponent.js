import React, { Component } from 'react';
import { getCatalog } from '../../bodyPage/bodyservices/catalogService';

class MainCatComponent extends Component{

    constructor(props) {
        super(props);
        this.state = {books: []};
    }

    componentDidMount() {
        getCatalog().then((response) => {
            //console.log('response:', JSON.stringify(response.data, null, 3));
            this.setState({books: response.data});
        });
    }
    
    render(){
       if(this.state.books === null){
        return(
            <div className="container-fluid">
                <table className ="table table-hover">
                    <thead>
                        <tr>
                            <th style={{'textAlign': 'center'}} colSpan={3}>Library Catalog</th>
                        </tr>
                        <tr>
                            <td>Title Id</td>
                            <td>Title</td>
                            <td>Author</td>
                        </tr>
                    </thead>
                </table>
            </div>
        ); 
       }
       else{
            return(
                <div className="container-fluid">
                    <table className ="table table-hover">
                        <thead>
                            <tr>
                                <th style={{'textAlign': 'center'}} colSpan={3}>Library Catalog</th>
                            </tr>
                            <tr>
                                <td>Title Id</td>
                                <td>Title</td>
                                <td>Author</td>
                            </tr>
                        </thead>
                        <tbody>
                            {this.state.books.map((item, index) => {
                                return(
                                    <tr key={"row" + index}>
                                        <td key={"book" + index}>{item.isbn}</td>
                                        <td key={"title" + index}>{item.title}</td>
                                        <td key={"author" + index}>{item.author}</td>
                                    </tr>
                                );
                            })}
                        </tbody>
                    </table>
                </div>
            );
        }
    }
}   


export default MainCatComponent;