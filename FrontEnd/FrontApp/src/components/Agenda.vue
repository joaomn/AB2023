<template>
<div class="inicio">

    <Button label="Inserir Contato" class="p-button-raised p-button-success" :style="{width: '400px', height:'45px'}" id="inseir" @click="openInsert" />
    
</div>

<Dialog v-model:visible="displayCreate" :style="{width: '900px'}"  :modal="true" class="p-fluid">
    <div class="card">
        <h2 class="text-center">Inserir Cadastro</h2>
        <form class="p-fluid" >
            <div class="field">
                <div class="p-inputgroup">
                    <span class="p-inputgroup-addon">
                        <i class="pi pi-user"></i>
                    </span>
                    <InputText placeholder="Nome" v-model="contato.name" required />
                </div>
            </div>
            <div class="field">
                <div class="p-inputgroup">
                    <span class="p-inputgroup-addon">
                        <i class="pi pi-envelope"></i>
                    </span>
                    <InputText placeholder="Email" v-model="contato.email"  />
                </div>
            </div>
            <div class="field">
                <div class="p-inputgroup">
                    <span class="p-inputgroup-addon">
                        <i class="pi pi-phone"></i>
                    </span>
                    <InputText placeholder="Telefone" v-model="contato.phone" required />
                </div>
            </div>
            <Button type="submit" label="Enviar" class="mt-2" @click="addAgenda" ></Button>
    
        </form>
    </div>
    </Dialog>

    <div class="table">
       
        <DataTable :value="usuarios" id="tabela">
            <column  id="colunas" field="name" header="Nome"></column>
            <column  id="colunas" field="email" header="E-mail"></column>
            <column id="colunas"  field="phone" header="Telefone"></column>           
            <Column id="colunas"  header="">
                <template #body="slotProps">
                    <Button label="Editar" id="botoes" class="p-button-rounded p-button-warning"
                        @click="openEdit(slotProps.data)" />
                    <Button label="Deletar" id="botoes" class="p-button-rounded p-button-danger"
                        @click="deleteAgenda(slotProps.data.id)" />
                </template>
            </Column>
        </DataTable>
    </div>

    <Dialog v-model:visible="displayBasic" :style="{width: '450px'}" header="Editar Cadastro" :modal="true"
        class="p-fluid">
        <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-user"></i>
                </span>
                <InputText placeholder="Nome" v-model="usuarios.name" />
            </div>
        </div>
        <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-envelope"></i>
                </span>
                <InputText placeholder="Email" v-model="usuarios.email" />
            </div>
        </div>
        <div class="field">
            <div class="p-inputgroup">
                <span class="p-inputgroup-addon">
                    <i class="pi pi-phone"></i>
                </span>
                <InputText placeholder="Telefone" v-model="usuarios.phone" />
            </div>
        </div>
        <template #footer>
            <Button label="Sair" icon="pi pi-times" @click="closeEdit" autofocus class="p-button-text" />
            <Button label="Salvar" icon="pi pi-check" @click="updateAgenda(usuarios.id), closeEdit" />
        </template>
    </Dialog>



</template>

<script>
import axios from 'axios';
export default {
    name: "Agenda",
    data() {
        return {
            usuarios: [],
            contato: {
                name: '',
            phone:'',
            email: ''
            },
            displayBasic: false,
            displayCreate: false



        }
    },
    async mounted() {
        this.loadData()
        
    },
    methods: {
        async loadData() {
            let result = await axios.get("http://localhost:8080/api/agenda");
            this.usuarios = result.data
            console.log(result.phone);
        },
        async addAgenda() {          
          
            const result = await axios.post("http://localhost:8080/api/agenda", this.contato)
            .then(result =>{})
                .catch(error => {
                    alert("Dados incorretos ou já cadastrados, por favor, verique os campos e tente novamente.");});
            
        },
        openInsert(agenda){
            this.displayCreate = true;
        },
        openEdit(agenda) {
            
            this.displayBasic = true;
            this.usuarios = { ...agenda };
        },
        closeEdit() {
            this.displayBasic = false;
            this.loadData()
        },
        async deleteAgenda(id) {
            let result = await axios.delete('http://localhost:8080/api/agenda/' + id);
            console.warn(result);
            if (result.status == 200)
                this.loadData()
        },
        async updateAgenda(id) {

            const result = await axios.put("http://localhost:8080/api/agenda/" + id, {
                name: this.usuarios.name,
                email: this.usuarios.email,
                phone: this.usuarios.phone,
            });

            if(result.status == 400){
                console.log(result.data)
                this.loadData();
            }
            
            
            if (result.status == 200)
                this.displayBasic = false;
            this.loadData();
        }
       

    },
}







</script>

<style>
.table {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 2px;
    width: 90%;
    padding: 5px;
    box-sizing: content-box;
    padding: 30px 40px 30px 40px;
   
}

#tabela {
    display: block;
    justify-content: center;
    align-items: center;
   box-sizing: content-box;
    padding: 2px;
    box-sizing: content-box;
    width: 90%;
    margin-left: 4rem;
    box-shadow: -10px 20px 20px 4px rgba(29, 39, 41, 0.801);


}

#botoes {
    margin: 4px;
}

#colunas{
    padding: 2px;
    width: 10px;
    box-sizing: content-box;
}
.inicio{
    display: flex;
    align-items: end;
    justify-content: end;
    width: 80%;
    margin-top: 9rem;
    margin-left: 130px;

}
#inserir{
    display: flex;
    width: 20rem;
    margin-left: 90px;
    
}
</style>