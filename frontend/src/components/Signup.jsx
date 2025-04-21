import React, { useState } from 'react'
import axios from 'axios'
import './Signup.css'

const Signup = () => {
    const [data, setData] = useState({
        name: "",
        email: "",
        password: ""
    })

    const handleChange = (e) => {
        const { name, value } = e.target
        setData({
            ...data,
            [name]: value
        })
    }

    const handleForm = async (e) => {
        e.preventDefault()
        try {
            const { name, email, password } = data
            const res = await axios.post("http://localhost:8080/api/signup", {
                name, email, password
            })
            console.log(res.data)

            if (res.status === 200) {
                alert("Signup successful!")
            } else {
                alert("Something went wrong!")
            }
        } catch (error) {
            console.error(error)
            alert("Server error!")
        }
    }

    return (
        <div className="signup-container">
            <div className="signup-card">
                <h2>Signup</h2>
                <form onSubmit={handleForm} className="signup-form">
                    <div className="input-group">
                        <input
                            type="text"
                            name="email"
                            placeholder="Email"
                            value={data.email}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="input-group">
                        <input
                            type="text"
                            name="name"
                            placeholder="Name"
                            value={data.name}
                            onChange={handleChange}
                        />
                    </div>
                    <div className="input-group">
                        <input
                            type="password"
                            name="password"
                            placeholder="Password"
                            value={data.password}
                            onChange={handleChange}
                        />
                    </div>
                    <button type="submit">Submit</button>
                </form>
            </div>
        </div>
    )
}

export default Signup
