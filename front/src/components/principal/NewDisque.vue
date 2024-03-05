<script setup> 
    import { reactive, onMounted } from 'vue';
    import axios from 'axios';
    import FormInsert from '../reuse/FormInsert.vue';
    import router from '@/router';

    var objectInsertDisque=reactive({
        idTypeDisque : {},

    });
    var responseInsertDisque=reactive({});

            const allIdTypeDisque=reactive({});


    onMounted(() => {
                const getAllIdTypeDisque = async () => {
            try {
                const urlApiSpringBoot = 'http://localhost:8080/typeDisque/allTypeDisque';
                const response = await axios.get(urlApiSpringBoot);
                Object.assign(allIdTypeDisque, response.data);
            } catch (error) {
                console.log(error.response);
            }
        };
        
        getAllIdTypeDisque();
 
    });

    const handleSubmit = () => {
        try {
            const url="http://localhost:8080/disque/insertDisque";
            const response=axios.post(url, objectInsertDisque);
            response.then(reponse => {
                Object.assign(responseInsertDisque, reponse.data);
                console.log(responseInsertDisque);
            })
            .catch(errors => {
                console.log(errors);
            })
            router.push("/allDisque");
        } catch(error) {
            console.log(error);
        }
    };
</script>
<template>
    <FormInsert title-props="Inserer une nouvelle disque" :handle-submit="handleSubmit" submit-message="Inserer la  disque">
        <input v-model="objectInsertDisque.capacite" type="number">
<input v-model="objectInsertDisque.vitesse" type="number">
<select  v-model="objectInsertDisque.idTypeDisque.idTypeDisque" id="">
    <option v-for="(idTypeDisque) in allIdTypeDisque" :key="idTypeDisque.idTypeDisque" :value="idTypeDisque.idTypeDisque">
        {{ idTypeDisque.label }}
    </option>
</select>

    </FormInsert>
</template>
<style scoped>
    @import "@/assets/css/formulaire.css";
</style>
