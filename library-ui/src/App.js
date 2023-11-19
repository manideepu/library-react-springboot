import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import ListBook from "./components/book/ListBook";
import HeaderComponent from "./components/static/HeaderComponent";
import FooterComponent from "./components/static/FooterComponent";
import AddBook from "./components/book/AddBook";
import DeleteBook from "./components/book/DeleteBook";
import BorrowBook from "./components/book/BorrowBook";
import ReturnBook from "./components/book/ReturnBook";
import ListAuthor from "./components/author/ListAuthor";
import AddAuthor from "./components/author/AddAuthor";
import UpdateBook from "./components/book/UpdateBook";

function App() {
    return (
        <div>
            <Router>
                <HeaderComponent/>
                <div className="container">
                    <Routes>
                        <Route path='/' element={<ListBook/>}/>
                        <Route path='/book/list' element={<ListBook/>}/>
                        <Route path='/book/add' element={<AddBook/>}/>
                        <Route path='/book/update/:id' element={<UpdateBook/>}/>
                        <Route path='/book/delete/:id' element={<DeleteBook/>}/>
                        <Route path='/book/borrow/:id' element={<BorrowBook/>}/>
                        <Route path='/book/return/:id' element={<ReturnBook/>}/>
                        <Route path='/author/list' element={<ListAuthor/>}/>
                        <Route path='/author/add' element={<AddAuthor/>}/>
                    </Routes>
                </div>
                <FooterComponent/>
            </Router>
        </div>
    );
}

export default App;
