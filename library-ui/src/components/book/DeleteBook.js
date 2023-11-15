import React from 'react'
import axios from 'axios'
import {useForm} from 'react-hook-form'
import {useState, useEffect} from 'react'
import {useNavigate, useParams, NavLink} from 'react-router-dom'

export default function DeleteBook() {
    const {id} = useParams();
    const navigate = useNavigate();
    const {handleSubmit} = useForm();
    const [book, setBook] = useState([]);

    async function fetchBook() {
        const result = await axios.get(`http://localhost:8080/v1/library/book/${id}`)
        setBook(result.data)
    }

    useEffect(() => {
        fetchBook();
    }, []);

    function deleteData() {
        axios.delete(`http://localhost:8080/v1/library/book/${id}`)
        navigate('/book/list')
    }

    return (
        <div className='container'>
            <br/><br/><br/>
            <form onSubmit={handleSubmit(deleteData)}>
                <h3>Are you sure to delete {book.name} ?</h3>
                <input type='submit' value='Yes' className='btn btn-danger'/>&nbsp;&nbsp;
                <NavLink to={'/'}>
                    <button className='btn btn-success'>No</button>
                </NavLink>
            </form>
        </div>
    );
}
