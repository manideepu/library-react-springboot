import React, {useEffect, useState} from 'react'
import axios from 'axios'
import {useForm} from 'react-hook-form'
import {NavLink, useNavigate, useParams} from 'react-router-dom'

export default function UpdateBook() {
    const {id} = useParams();
    const {register, handleSubmit, setValue} = useForm();
    const navigate = useNavigate();
    const [authors, setAuthors] = useState([]);


    const updateBook = data => {
        axios.put(`http://localhost:8080/v1/library/book/${id}`, data).then(r => navigate('/book/list'));
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

        async function fetchBook() {
            const result = await axios.get(`http://localhost:8080/v1/library/book/${id}`)
            setValue('title', result.data.title);
            setValue('publisher', result.data.publisher);
            setValue('authorId', result.data.authorId);
        }

        fetchBook();
        fetchAuthors();
    }, []);


    return (
        <div className='container'>
            <form onSubmit={handleSubmit(updateBook)} className='jumbotron mt-4'>
                <label htmlFor='bookTitle'><b>Title</b></label>
                <input id='bookTitle' type='text' className='form-control' {...register('title')} />
                <br/>
                <label htmlFor='bookPublisher'><b>Book Publisher</b></label>
                <input id='bookPublisher' type='text' className='form-control' {...register('publisher')}/>
                <br/>
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
                <input type='submit' className='btn btn-success' value="Update"/> &nbsp;
                <NavLink to={'/'}>
                    <button className='btn btn-warningr'>Cancel</button>
                </NavLink>
            </form>
        </div>
    );
}
