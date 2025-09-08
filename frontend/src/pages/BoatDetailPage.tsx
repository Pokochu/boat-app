import { useEffect, useState } from "react";
import { useParams } from "react-router-dom";
import axiosInstance from "../api/axiosInstance";

interface Boat {
  id: number;
  name: string;
  description: string;
}

const BoatDetailPage = () => {
  const { id } = useParams<{ id: string }>();
  const [boat, setBoat] = useState<Boat | null>(null);
  const [name, setName] = useState('');
  const [description, setDescription] = useState('');

  useEffect(() => {
    axiosInstance.get(`/boats/${id}`)
      .then(res => {
        setBoat(res.data);
        setName(res.data.name);
        setDescription(res.data.description);
      })
      .catch(err => console.error(err));
  }, [id]);

  const handleUpdate = () => {
    axiosInstance.put(`/boats/${id}`, { name, description })
      .then(res => {
        console.log("Boat updated successfully!", res.data);
      })
      .catch(err => console.error(err));
  };

  if (!boat) return <div>Loading...</div>;

  return (
    <div className="page-container">
      <form onSubmit={handleUpdate} className="form-card">
        <h2>Edit Boat</h2>
        <input
          type="text"
          placeholder="Name"
          value={name}
          onChange={(e) => setName(e.target.value)}
        />
        <textarea
          placeholder="Description"
          value={description}
          onChange={(e) => setDescription(e.target.value)}
        />
        <button type="submit">Update</button>
      </form>
    </div>
  );
};

export default BoatDetailPage;