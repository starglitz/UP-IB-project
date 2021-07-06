import AxiosClient from "./clients/AxiosClient";

export const RecipeService = {
    getAll,
    approve,
    create,
    getNotApproved
};

async function getAll() {
    return await AxiosClient.get(`https://localhost:8080/recipes`);
}

async function getNotApproved(id) {
    return await AxiosClient.get(`https://localhost:8080/recipes/nurse/${id}`);
}

async function approve(recipe_id, recipe) {
    return await AxiosClient.put(`https://localhost:8080/recipes/approve/${recipe_id}`, recipe);
}

async function create(recipe) {
    return await AxiosClient.post("https://localhost:8080/recipes", recipe);
}