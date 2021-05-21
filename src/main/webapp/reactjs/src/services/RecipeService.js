import AxiosClient from "./clients/AxiosClient";

export const RecipeService = {
    getAll,
    approve,
    create,
    getNotApproved
};

async function getAll() {
    return await AxiosClient.get(`http://localhost:8080/recipes`);
}

async function getNotApproved() {
    return await AxiosClient.get(`http://localhost:8080/recipes/notApproved`);
}

async function approve(recipe_id, recipe) {
    return await AxiosClient.put(`http://localhost:8080/recipes/${recipe_id}`, recipe);
}

async function create(recipe) {
    return await AxiosClient.post("http://localhost:8080/recipes", recipe);
}