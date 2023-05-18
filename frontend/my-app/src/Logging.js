import React, { useState } from 'react';

function Logging(){
    const [email, setEmail] = useState('');
    const [password, setPassword] = useState('');

    const handleEmail = (e) => {
        setEmail(e.target.value);
    };

    const handlePassword = (e) => {
        setPassword(e.target.value);
    };

    const handleSubmit = (e) => {
        e.preventDefault()

        const loggingData = {
            email, password
        };

        fetch('http://localhost:8080/api/v1/auth/authenticate', {
            method: 'POST',
            headers: {
                'Content-type': 'application/json',
            },
            body: JSON.stringify(loggingData),
        })
            .then((response) => response.json())
            .then((data) =>{
                console.log(data);
            })
            .catch((error) => {
                console.error(error);
            });
    };
    return (
        <div>
            <h2>Log in</h2>
        <form onSubmit={handleSubmit}>
            <label>
                Email:
                <input type="text" value={email} onChange={handleEmail} />
            </label>
            <br />
            <label>
                Password:
                <input type="text" value={password} onChange={handlePassword} />
            </label>
            <br />
            <button type="submit">Log in</button>
        </form>
        </div>
    )
}

export default Logging;