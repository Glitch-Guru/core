import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import Registration from './Registration';
import Logging from "./Logging";
import CreateTicket from "./CreateTicket";

function App() {
    return (
        <Router>
            <div className="App">
                <Routes>
                    <Route path="/register" element={<Registration />} />
                    <Route path="/login" element={<Logging />} />
                    <Route path="/createticket" element={<CreateTicket/>} />
                    {/* Define more routes for other components/pages */}
                </Routes>
            </div>
        </Router>
    );
}

export default App;