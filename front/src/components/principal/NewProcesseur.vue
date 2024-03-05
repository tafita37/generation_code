<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertProcesseur=reactive({
        
    });
    var responseInsertProcesseur=reactive({});

    

    onMounted(() => {
         
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/processeur/insertProcesseur";
            const response=axios.post(url, objectInsertProcesseur);
            response.then(reponse => {
                Object.assign(responseInsertProcesseur, reponse.data);
                console.log(responseInsertProcesseur);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allProcesseur");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle processeur" :handle-submit="handleSubmit" submit-message="Inserer la  processeur">
        <input v-model="objectInsertProcesseur.nomProcesseur" type="text">
<input v-model="objectInsertProcesseur.frequence" type="number">
<input v-model="objectInsertProcesseur.nbCoeur" type="number">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
