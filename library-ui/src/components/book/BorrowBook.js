import {useForm} from "react-hook-form";
import {NavLink, useNavigate, useParams} from "react-router-dom";
import React, {useEffect, useState} from "react";
import axios from "axios";

export default function BorrowBook() {
    const {id} = useParams();
    const {register, handleSubmit, setValue} = useForm();
    const navigate = useNavigate();
    const [users, setUsers] = useState([]);
    const [bookName, setBookName] = useState();
       const borrowBook = async data => {
        await axios.put(`http://localhost:8080/v1/library/book/borrow/${id}/${data.userId}`, data)
        navigate('/')
    };

    useEffect(() => {
        async function fetchUsers() {
            // Fetch data
            const {data} = await axios.get("http://localhost:8080/v1/library/user/list");
            const results = []
            data.forEach((value) => {
                results.push({
                    key: value.fullName,
                    value: value.id,
                });
            });
            setUsers([
                {key: 'Select borrowing User', value: ''},
                ...results
            ])
        }

        async function fetchBook() {
            const {data} = await axios.get(`http://localhost:8080/v1/library/book/${id}`);
            setBookName(data.title);
        }

        fetchUsers();
        fetchBook()
    }, []);
    return (
        <div className='container'>
            <form onSubmit={handleSubmit(borrowBook)} className='jumbotron mt-4'>
                <label htmlFor='bookName'><b>Book Title:-&nbsp;&nbsp;&nbsp;</b></label>
                <label htmlFor='bookName'>{bookName}</label><br/><br/>
                <label htmlFor='authorName'><b>Borrowing User</b></label><br/>
                <select {...register('userId')} id='userId' className='form-control'>
                    {
                        users.map((user) => {
                                return (
                                    <option key={user.value} value={user.value}>{user.key}</option>
                                );
                            }
                        )
                    }
                </select>
                <br/>
                <input type='submit' className='btn btn-success' value="Borrow"/> &nbsp;
                <NavLink to={'/'}>
                    <button className='btn btn-warningr'>Cancel</button>
                </NavLink>
            </form>
        </div>
    );
}
