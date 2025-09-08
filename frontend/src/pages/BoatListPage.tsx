// BoatListPage.tsx
import { useEffect, useState } from "react";
import { Link, useNavigate } from "react-router-dom";
import axiosInstance from "../api/axiosInstance";

interface Boat {
  id: number;
  name: string;
  description: string;
}

const BoatListPage = () => {
  const [boats, setBoats] = useState<Boat[]>([]);
  const navigate = useNavigate();

  const fetchBoats = () => {
    axiosInstance.get("/boats")
      .then(res => setBoats(res.data))
      .catch(err => console.error(err));
  };

  const deleteBoat = (id: number) => {
    if (!window.confirm("Are you sure?")) return;
    axiosInstance.delete(`/boats/${id}`)
      .then(fetchBoats)
      .catch(err => console.error(err));
  };

  useEffect(() => {
    fetchBoats();
  }, []);

  return (
    <div className="list-page-container">
      <h2>Boat List</h2>
      <Link to="/boats/new" className="add-boat-link">Add New Boat</Link>
      <div className="boat-list-container">
        <ul className="boat-list">
          {boats.map(boat => (
            <li key={boat.id} className="boat-item">
              <div className="boat-details">
                <Link to={`/boats/${boat.id}`} className="boat-name-link">{boat.name}</Link> -
                <span className="boat-description">{boat.description}</span>
              </div>
              <div className="boat-actions">
                <button onClick={() => navigate(`/boats/${boat.id}/edit`)} className="edit-button">Edit</button>
                <button onClick={() => deleteBoat(boat.id)} className="delete-button">Delete</button>
              </div>
            </li>
          ))}
        </ul>
      </div>
    </div>
  );
};

export default BoatListPage;