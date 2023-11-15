import React, {useEffect, useState} from 'react'
import axios from 'axios'
import {useForm} from 'react-hook-form'
import {NavLink, useNavigate} from 'react-router-dom'

export default function AddAuthor() {
    const {register, handleSubmit} = useForm();
    const navigate = useNavigate();
    const saveBook = data => {
        axios.post('http://localhost:8080/v1/library/author', data).then(r =>  navigate('/author/list'));

    };

    return (
        <div className='container'>
            <form onSubmit={handleSubmit(saveBook)} className='jumbotron mt-4'>
                <label htmlFor='firstName'><b>Author First Name</b></label>
                <input id='firstName' type='text' className='form-control'
                       placeholder='eg. William' {...register('firstName')}/><br/>
                <label htmlFor='lastName'><b>Author Last Name</b></label>
                <input id='lastName' type='text' className='form-control'
                       placeholder='eg. Shakespeare' {...register('lastName')}/><br/>
                <label htmlFor='email'><b>Author Email</b></label>
                <input id='email' type='text' className='form-control'
                       placeholder='eg. William.Shakespeare@test.com' {...register('email')}/><br/>
                <br/>
                <input type='submit' className='btn btn-success' value="Add"/> &nbsp;
                <NavLink to={'/author/list'}>
                    <button className='btn btn-warningr'>Cancel</button>
                </NavLink>
            </form>
        </div>
    );
}
