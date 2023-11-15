import React from 'react'
import axios from 'axios'
import {useEffect, useState} from 'react'
import {Link, NavLink} from 'react-router-dom'

export default function ListAuthor() {
    const [authors, setAuthors] = useState([]);

    async function listAllAuthors() {
        const result = await axios.get('http://localhost:8080/v1/library/author/list')
        console.log(result.data)
        setAuthors(result.data)
    }

    useEffect(() => {
        listAllAuthors();
    }, []);
    return (
        <div>
            <br/>
            <br/>
            &nbsp;
            {
                <Link to={'/'}>
                    <a className='btn btn-warning'>Book</a>
                </Link>
            }&nbsp;&nbsp;
            {
                <Link to={'/author/add'}>
                    <a className='btn btn-warning'>Add Author</a>
                </Link>

            }
            <br/>
            <br/>
            <table className='table table-striped table-bordered'>
                <thead>
                <tr align="center">
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                </tr>
                </thead>
                <tbody>
                {
                    authors.map(author => {
                            return (
                                <tr key={author.id}>
                                    <td>{author.firstName}</td>
                                    <td>{author.lastName}</td>
                                    <td>{author.email}</td>
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
