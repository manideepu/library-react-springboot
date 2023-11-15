import {useForm} from "react-hook-form";
import {NavLink, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";

export default function ReturnBook() {
    const {id} = useParams();
    const {register, handleSubmit, setValue} = useForm();
    const navigate = useNavigate();
    const [users, setUsers] = useState([]);
    const [bookName, setBookName] = useState();
    const [borrowerId, setReturnUserId] = useState();
    const borrowBook = async data => {
        console.log("borrowerId="+borrowerId);
        await axios.put(`http://localhost:8080/v1/library/book/return/${id}/${borrowerId}`)
        navigate('/')
    };

    useEffect(() => {
        async function fetchBook() {
            const {data} = await axios.get(`http://localhost:8080/v1/library/book/${id}`);
            setBookName(data.title);
            setReturnUserId(data.borrowerId);
        }
        fetchBook()
    }, []);
    return (
        <div className='container'>
            <form onSubmit={handleSubmit(borrowBook)} className='jumbotron mt-4'>
                <label htmlFor='bookName'><b>Book Title:-&nbsp;&nbsp;&nbsp;</b></label>
                <label htmlFor='bookName'>{bookName}</label><br/><br/>
                <input type='submit' className='btn btn-success' value="Return"/> &nbsp;
                <NavLink to={'/'}>
                    <button className='btn btn-warningr'>Cancel</button>
                </NavLink>
            </form>
        </div>
    );
}
