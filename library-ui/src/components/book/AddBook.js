import React, {useEffect, useState} from 'react'
import axios from 'axios'
import {useForm} from 'react-hook-form'
import {NavLink, useNavigate} from 'react-router-dom'

export default function AddBook() {
    const {register, handleSubmit} = useForm();
    const navigate = useNavigate();
    const [authors, setAuthors] = useState([]);
    const saveBook = data => {
        axios.post('http://localhost:8080/v1/library/book', data).then(r => navigate('/book/list'));
    };

    useEffect(() => {
        async function fetchAuthors() {
            // Fetch data
            const {data} = await axios.get("http://localhost:8080/v1/library/author/list");
            const results = []
            data.forEach((value) => {
                results.push({
                    key: value.fullName,
                    value: value.id,
                });
            });
            setAuthors([
                {key: 'Select an Author', value: ''},
                ...results
            ])
        }

        fetchAuthors();
    }, []);


    return (
        <div className='container'>
            <form onSubmit={handleSubmit(saveBook)} className='jumbotron mt-4'>
                <label htmlFor='bookTitle'><b>Book Name</b></label>
                <input id='bookTitle' type='text' className='form-control'
                       placeholder='eg. Midnight Is the Darkest Hour' {...register('title')}/><br/>
                <label htmlFor='bookPublisher'><b>Book Publisher</b></label>
                <input id='bookPublisher' type='text' className='form-control'
                       placeholder='eg. Midnight Is the Darkest Hour' {...register('publisher')}/><br/>
                <label htmlFor='authorName'><b>Author Name</b></label>
                <select {...register('authorId')} id='authorId' className='form-control'>
                    {
                        authors.map((author) => {
                                return (
                                    <option key={author.value} value={author.value}>{author.key}</option>
                                );
                            }
                        )
                    }
                </select>
                <br/>
                <input type='submit' className='btn btn-success' value="Add"/> &nbsp;
                <NavLink to={'/'}>
                    <button className='btn btn-warningr'>Cancel</button>
                </NavLink>
            </form>
        </div>
    );
}
