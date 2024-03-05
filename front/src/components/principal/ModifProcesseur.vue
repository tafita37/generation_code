<script setup>
    import { onMounted, reactive } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import { useRoute } from 'vue-router';
    import router from '@/router';

    const route=useRoute();
    const objectInsertProcesseur = reactive({
        idProcesseur:route.query.idProcesseur,
        
    });

    const responseInsertProcesseur = reactive({});
    
    

    onMounted(() => {
         
    });

    const handleSubmit = async () => {
        try {
            const url = 'http://localhost:8080/processeur/insertProcesseur';
            const response = await axios.post(url, objectInsertProcesseur);
            Object.assign(responseInsertProcesseur, response);
            router.push("/allProcesseur");
        } catch (error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Modifier le/la processeur" :handle-submit="handleSubmit" submit-message="Modifier">
        <input v-model="objectInsertProcesseur.nomProcesseur" type="text">
<input v-model="objectInsertProcesseur.frequence" type="number">
<input v-model="objectInsertProcesseur.nbCoeur" type="number">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
