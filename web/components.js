(() => {
const $=s=>document.querySelector(s), $$=s=>[...document.querySelectorAll(s)];
const dialog=$("#demo-dialog"),sheet=$("#demo-sheet"),menu=$("#demo-menu"),trigger=$("#menu-trigger"),snack=$("#snackbar"),toast=$("#toast-alert");let snackTimer,toastTimer,page=1;
function notify(message){$("#snackbar-message").textContent=message;snack.classList.add("visible");clearTimeout(snackTimer);snackTimer=setTimeout(()=>snack.classList.remove("visible"),3500)}
function alert(message){toast.textContent=message;toast.hidden=false;clearTimeout(toastTimer);toastTimer=setTimeout(()=>toast.hidden=true,4000)}
function closeMenu({restoreFocus=false}={}){menu.hidden=true;trigger.setAttribute("aria-expanded","false");if(restoreFocus)trigger.focus()}
function menuItems(){return $$("#demo-menu [data-menu-action]")}
function focusMenuItem(index){const items=menuItems();if(items.length)items[(index+items.length)%items.length].focus()}
function openMenu(){menu.hidden=false;trigger.setAttribute("aria-expanded","true");focusMenuItem(0)}
$("#primary-action").addEventListener("click",()=>notify("Botão principal acionado."));
$("#dialog-open").addEventListener("click",()=>dialog.showModal());$$("[data-dialog-close]").forEach(b=>b.addEventListener("click",()=>dialog.close()));$("#dialog-confirm").addEventListener("click",()=>{dialog.close();notify("Ação confirmada.")});dialog.addEventListener("click",e=>{if(e.target===dialog)dialog.close()});
$("#sheet-open").addEventListener("click",()=>sheet.showModal());$$("[data-sheet-close]").forEach(b=>b.addEventListener("click",()=>sheet.close()));sheet.addEventListener("click",e=>{if(e.target===sheet)sheet.close()});
trigger.addEventListener("click",()=>menu.hidden?openMenu():closeMenu());
trigger.addEventListener("keydown",e=>{if(e.key==="ArrowDown"||e.key==="Enter"||e.key===" "){e.preventDefault();openMenu()}});
menuItems().forEach(b=>b.addEventListener("click",()=>{notify(b.dataset.menuAction+" selecionado.");closeMenu({restoreFocus:true})}));
menu.addEventListener("keydown",e=>{
 const items=menuItems(),index=items.indexOf(document.activeElement);
 if(e.key==="Escape"){e.preventDefault();closeMenu({restoreFocus:true})}
 else if(e.key==="ArrowDown"){e.preventDefault();focusMenuItem(index+1)}
 else if(e.key==="ArrowUp"){e.preventDefault();focusMenuItem(index-1)}
 else if(e.key==="Home"){e.preventDefault();focusMenuItem(0)}
 else if(e.key==="End"){e.preventDefault();focusMenuItem(items.length-1)}
});
document.addEventListener("click",e=>{if(!e.target.closest(".menu-wrap")&&!menu.hidden)closeMenu()});
document.addEventListener("keydown",e=>{if(e.key==="Escape"&&!menu.hidden)closeMenu({restoreFocus:true})});
const tabs=$$("#componentes [role=tab]"),copies=["Resumo do projeto e informações principais.","Acompanhe mudanças recentes e eventos do projeto.","Consulte arquivos, exemplos e recursos disponíveis."];tabs.forEach((tab,i)=>{tab.addEventListener("click",()=>{tabs.forEach((t,j)=>{t.setAttribute("aria-selected",String(i===j));t.tabIndex=i===j?0:-1});$("#tab-panel").textContent=copies[i];$("#tab-panel").setAttribute("aria-labelledby",tab.id)});tab.addEventListener("keydown",e=>{if(!["ArrowRight","ArrowLeft","Home","End"].includes(e.key))return;e.preventDefault();const n=e.key==="Home"?0:e.key==="End"?tabs.length-1:(i+(e.key==="ArrowRight"?1:tabs.length-1))%tabs.length;tabs[n].focus();tabs[n].click()})});
$("#density").addEventListener("input",e=>$("#density-value").textContent=e.target.value+"%");
$("#loading-toggle").addEventListener("click",()=>{const el=$("#skeleton-demo"),loading=el.dataset.loading!=="true";el.dataset.loading=String(loading);el.replaceChildren();if(loading){["skeleton-title","skeleton","skeleton-short"].forEach(className=>{const line=document.createElement("div");line.className="skeleton "+className;el.append(line)})}else{const strong=document.createElement("strong");strong.style.fontSize="13px";strong.textContent="Conteúdo carregado";const helper=document.createElement("span");helper.className="helper";helper.textContent="Carregamento finalizado.";el.append(strong,helper)}$("#loading-toggle").textContent=loading?"Mostrar conteúdo":"Alternar skeleton"});
$("#snackbar-show").addEventListener("click",()=>notify("Preferências salvas com sucesso."));$("#snackbar-close").addEventListener("click",()=>snack.classList.remove("visible"));$("#alert-show").addEventListener("click",()=>alert("Esta é uma mensagem de alerta temporária."));
$("#demo-form").addEventListener("submit",e=>{e.preventDefault();const name=$("#project-name"),email=$("#project-email"),okName=name.value.trim().length>=3,okEmail=/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email.value.trim());name.closest(".field").classList.toggle("invalid",!okName);email.closest(".field").classList.toggle("invalid",!okEmail);$("#project-error").textContent=okName?"Nome válido.":"Informe pelo menos 3 caracteres." ;$("#email-error").textContent=okEmail?"E-mail válido.":"Informe um e-mail válido.";name.setAttribute("aria-invalid",String(!okName));email.setAttribute("aria-invalid",String(!okEmail));if(okName&&okEmail)notify("Formulário validado com sucesso.");else(!okName?name:email).focus()});$("#demo-form").addEventListener("input",e=>{if(e.target.matches("input")){const field=e.target.closest(".field");field.classList.remove("invalid");e.target.setAttribute("aria-invalid","false")}});
const rows=[["UI","Fundamentos visuais","Tokens, cores e tipografia","Base"],["CP","Componentes","Controles reutilizáveis","Web"],["AX","Acessibilidade","Teclado, foco e semântica","A11y"],["TH","Temas","Esquemas claro e escuro","Theme"],["DC","Documentação","Guias e exemplos","Docs"],["AN","Android","Componentes Jetpack Compose","Mobile"],["DS","Design tokens","Valores semânticos e primitivos","Core"],["TM","Templates","Layouts de produto","UI"],["QA","Qualidade","Testes e critérios","QA"]];
function render(){const size=3,start=(page-1)*size,total=Math.ceil(rows.length/size),list=$("#paginated-list");list.replaceChildren();rows.slice(start,start+size).forEach(r=>{const item=document.createElement("li"),avatar=document.createElement("span"),body=document.createElement("span"),title=document.createElement("strong"),description=document.createElement("small"),trailing=document.createElement("span");avatar.className="avatar";avatar.textContent=r[0];title.textContent=r[1];description.textContent=r[2];body.append(title,description);trailing.className="list-trailing";trailing.textContent=r[3];item.append(avatar,body,trailing);list.append(item)});$("#page-status").textContent="Página "+page+" de "+total;$("#page-prev").disabled=page===1;$("#page-next").disabled=page===total;$(".page-number").textContent=page}
$("#page-prev").addEventListener("click",()=>{page=Math.max(1,page-1);render()});$("#page-next").addEventListener("click",()=>{page=Math.min(Math.ceil(rows.length/3),page+1);render()});
const spin=$("#spinner-toggle");spin.addEventListener("click",()=>{spin.disabled=true;spin.textContent="Aguarde...";setTimeout(()=>{spin.disabled=false;spin.textContent="Carregamento concluído"},1200)});

if (window.MinimalIcons) {
  MinimalIcons.mountAll();
  const gallery = $("#icon-gallery");
  if (gallery) MinimalIcons.names.forEach(name => {
    const tile = document.createElement("div");
    const caption = document.createElement("span");
    tile.className = "icon-tile";
    tile.append(MinimalIcons.create(name, { size: 23 }));
    caption.textContent = name;
    tile.append(caption);
    gallery.append(tile);
  });
}
const aiForm = $("#ai-demo-form");
if (aiForm) aiForm.addEventListener("submit", event => {
  event.preventDefault();
  const prompt = $("#ai-prompt"), value = prompt.value.trim(), messages = $("#ai-messages");
  if (!value) { prompt.focus(); return; }
  const user = document.createElement("div"), userAvatar = document.createElement("span"), userBubble = document.createElement("div");
  user.className = "ai-message user"; userAvatar.className = "ai-avatar"; userAvatar.append(MinimalIcons.create("user"));
  userBubble.className = "ai-bubble"; userBubble.textContent = value; user.append(userAvatar, userBubble); messages.append(user);
  const assistant = document.createElement("div"), avatar = document.createElement("span"), bubble = document.createElement("div"), heading = document.createElement("strong");
  assistant.className = "ai-message"; avatar.className = "ai-avatar"; avatar.append(MinimalIcons.create("sparkles"));
  bubble.className = "ai-bubble"; heading.textContent = "Assistente"; bubble.append(heading, document.createTextNode("Esta é uma resposta ilustrativa. Conecte seu próprio modelo e backend para gerar respostas reais."));
  assistant.append(avatar, bubble); messages.append(assistant); prompt.value = ""; messages.scrollTop = messages.scrollHeight;
});
const browserForm = $("#browser-address-form");
if (browserForm) browserForm.addEventListener("submit", event => {
  event.preventDefault();
  const value = $("#browser-url").value.trim();
  $("#browser-page-title").textContent = value || "Nova página";
  $("#browser-page-status").textContent = "Endereço atualizado apenas nesta demonstração.";
});
const newTab = $("#browser-new-tab");
if (newTab) newTab.addEventListener("click", () => {
  const count = $("#browser-tab-count");
  count.textContent = String(Number(count.textContent) + 1);
  $("#browser-page-title").textContent = "Nova aba";
  $("#browser-page-status").textContent = "Nova aba demonstrativa criada.";
});
const browserTabs = $("#browser-tabs");
if (browserTabs) browserTabs.addEventListener("click", () => {
  const count = $("#browser-tab-count").textContent;
  $("#browser-page-status").textContent = count + (count === "1" ? " aba demonstrativa aberta." : " abas demonstrativas abertas.");
});
const browserHome = $("#browser-home");
if (browserHome) browserHome.addEventListener("click", () => {
  $("#browser-url").value = "minimal.local/inicio";
  $("#browser-page-title").textContent = "Navegue com simplicidade";
  $("#browser-page-status").textContent = "Página de demonstração local.";
});

render();
})();