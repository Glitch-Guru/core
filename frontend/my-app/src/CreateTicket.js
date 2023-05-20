import React, { useState } from 'react';


function CreateTicket() {
    const [title, setTitle] = useState('');
    const [description, setDescription] = useState('');
    const [status, setStatus] = useState('');
    const [assigneeId, setAssigneeId] = useState('');


    const handleTitle = (e) => {
        setTitle(e.target.value);
    };

    const handleDescription = (e) => {
        setDescription(e.target.value);
    };

    const handleStatus = (e) => {
        setStatus(e.target.value);
    };

    const handleAssigneeId = (e) => {
        setAssigneeId(e.target.value);
    };
    // Event handler for form submission
    const handleSubmit = (e) => {
        e.preventDefault();

        var token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlIjoiVVNFUiIsInN1YiI6I…zczfQ.dodQk7Yco91M4ggUhsj08gPlzGUYi08ez_TFb9vy_C8";
        const ticketData = {
            title,
            description,
            status,
            assigneeId
        };

        fetch('http://localhost:8080/api/v1/tickets', {
            method: 'POST',
            headers: {
                Authorization: `Bearer ${encodeURIComponent(token)}`,
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(ticketData),
        })
            .then((response) => response.json())
            .then((data) => {
                console.log(data);
            })
            .catch((error) => {
                console.error(error);
            });
    };
    return (
        <div>
            <h2>Create ticket</h2>
            <form onSubmit={handleSubmit}>
                <label>
                    Title:
                    <input type="text" value={title} onChange={handleTitle} />
                </label>
                <br />
                <label>
                    Description:
                    <input type="text" value={description} onChange={handleDescription} />
                </label>
                <br />
                <label>
                    Status:
                    <input type="text" value={status} onChange={handleStatus} />
                </label>
                <br />
                <label>
                    Assigned to:
                    <input type="text" value={assigneeId} onChange={handleAssigneeId} />
                </label>
                <br />
                <button type="submit">Create ticket</button>
            </form>
        </div>
    );
}

export default CreateTicket;
