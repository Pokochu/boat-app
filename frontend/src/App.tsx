import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { AuthProvider } from "./context/AuthContext";
import LoginPage from "./pages/LoginPage";
import BoatListPage from "./pages/BoatListPage";
import BoatDetailPage from "./pages/BoatDetailPage";
import BoatFormPage from "./pages/BoatFormPage";
import './index.css';

function App() {
  return (
    <AuthProvider>
      <Router>
        <Routes>
          <Route path="/" element={<LoginPage />} />
          <Route path="/boats" element={<BoatListPage />} />
          <Route path="/boats/new" element={<BoatFormPage />} />
          <Route path="/boats/:id/edit" element={<BoatFormPage />} />
          <Route path="/boats/:id" element={<BoatDetailPage />} />
        </Routes>
      </Router>
    </AuthProvider>
  );
}

export default App;
