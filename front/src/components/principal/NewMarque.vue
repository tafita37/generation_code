<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertMarque=reactive({
        
    });
    var responseInsertMarque=reactive({});

    

    onMounted(() => {
         
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/marque/insertMarque";
            const response=axios.post(url, objectInsertMarque);
            response.then(reponse => {
                Object.assign(responseInsertMarque, reponse.data);
                console.log(responseInsertMarque);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allMarque");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle marque" :handle-submit="handleSubmit" submit-message="Inserer la  marque">
        <input v-model="objectInsertMarque.nomMarque" type="text">

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
