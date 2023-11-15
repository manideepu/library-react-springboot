import React from 'react'
import axios from 'axios'
import {useEffect, useState} from 'react'
import {Link, NavLink} from 'react-router-dom'
import {FaArrowDown, FaArrowUp} from "react-icons/fa";

export default function ListBook() {
    const [books, setBooks] = useState([]);
    const [sorted, setSorted] = useState({sorted: "title", reversed: false});

    const sortByName = () => {
        setSorted({
            sorted: "title", reversed: !sorted.reversed
        });
        const booksCopy = [...books];
        booksCopy.sort((book1, book2) => {
            if (sorted.reversed) {
                return book2.title.localeCompare(book1.title);
            }
            return book1.title.localeCompare(book2.title);
        });
        setBooks(booksCopy);
    };

    async function listAllBooks() {
        const result = await axios.get('http://localhost:8080/v1/library/book/list');
        console.log(result.data);
        setBooks(result.data);
    }

    const renderArrow = () => {
        if (sorted.reversed) {
            return <FaArrowUp/>;
        }
        return <FaArrowDown/>;
    }

    useEffect(() => {
        listAllBooks();

    }, []);
    return (
        <div>
            <br/>
            <br/>
            {
                <Link to={'/author/list'}>
                    <a className='btn btn-warning'>Authors</a>
                </Link>
            }&nbsp;
            {
                <Link to={'/book/add'}>
                    <a className='btn btn-warning'>Add Book</a>
                </Link>
            }
            <br/>
            <br/>
            <table className='table table-striped table-bordered'>
                <thead>
                <tr align="center">
                    <th onClick={sortByName}><span
                        style={{marginRight: 10}}>Title</span> {sorted.sorted === 'title' ? renderArrow() : null}</th>
                    <th>Published By</th>
                    <th>Author Name</th>
                    <th>Available</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {
                    books.map(book => {
                            return (
                                <tr key={book.id}>
                                    <td>{book.title}</td>
                                    <td>{book.publisher}</td>
                                    <td>{book.author.firstName} {book.author.lastName}</td>
                                    <td align="center">{book.borrowed ? 'No' : 'Yes'}</td>
                                    <td align="center">
                                        <NavLink to={`/book/update/${book.id}`}>
                                            <button className='btn btn-warning'>Update</button>
                                        </NavLink> &nbsp;
                                        <NavLink to={`/book/delete/${book.id}`}>
                                            <button className='btn btn-danger'>Delete</button>
                                        </NavLink>&nbsp;

                                        <button className='btn btn-primary' disabled={book.borrowed}>
                                            <NavLink to={`/book/borrow/${book.id}`} className="borrow">Borrow</NavLink>
                                        </button>
                                        &nbsp;
                                        <button className='btn btn-primary' disabled={!book.borrowed}>
                                            <NavLink to={`/book/return/${book.id}`} className="borrow">Return</NavLink>
                                        </button>
                                    </td>
                                </tr>
                            )
                        }
                    )
                }
                </tbody>
            </table>
        </div>
    )
        ;
}
