import LoginPage from './pages/LoginPage'
import SignUpPage from './pages/SignUpPage';
import './styles/App.css'
import { BrowserRouter as Router, Routes, Route} from 'react-router-dom';

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<LoginPage />} />
        <Route path="/signUp" element={<SignUpPage />} />
      </Routes>
    </Router>
  )
}

export default App
