import BodyComponent from '../components/bodyPage/bodyComponent';
import HeaderComponent from '../components/headerPage/headerComponent';
import CatalogComponent from '../components/bodyPage/catalogComponent';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import './App.css';

function App() {
  return (
    <div className="App">
      <HeaderComponent/>

      <div className="container-fluid">
        <Router>
          <Routes>
            <Route path="/" exact element={<BodyComponent/>}/>
            <Route path="/dashboard" exact element={<BodyComponent/>}/>
            <Route path="/catalog" exact element={<CatalogComponent/>}/>
          </Routes>
        </Router>
      </div>
    </div>
  );
}

export default App;
