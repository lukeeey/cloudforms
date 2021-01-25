# CloudForms
A callback-based form library for Cloudburst, Nukkit and any other project you may need it for.

### Note
CloudForms is NOT compatible with the Nukkit Form API. An implementation example can be seen below:
```java
// MyPlayerSession
private final AtomicInteger formIdCounter = new AtomicInteger(0);
private final Int2ObjectMap<Form> forms = new Int2ObjectOpenHashMap<>();

public void sendForm(Form form) {
    int formId = formIdCounter.incrementAndGet();

    ModalFormRequestPacket modalFormRequestPacket = new ModalFormRequestPacket();
    modalFormRequestPacket.formId = formId;
    modalFormRequestPacket.data = form.serialize().toString();

    player.dataPacket(modalFormRequestPacket);
    forms.put(formId, form);
}


// EventListener
@EventHandler
public void onPacketReceive(DataPacketReceiveEvent event) {
    if (event.getPacket() instanceof ModalFormResponsePacket) {
        ModalFormResponsePacket packet = (ModalFormResponsePacket) event.getPacket();
        MyPlayerSession session = (MyPlayerSession) SessionManager.get(event.getPlayer().getUniqueId());

        if(!session.getForms().containsKey(packet.formId)) {
            event.getPlayer().sendMessage(C.RED + "Form id from response does not exist in the map!");
            return;
        }
        session.getForms().get(packet.formId).handleResponse(packet.data);
    }
}
```

### Including CloudForms in your project
For now, the only way is to use JitPack:
```xml
<repository>
    <id>jitpack.io</id>
    <url>https://jitpack.io</url>
</repository>
```
```xml
<dependency>
    <groupId>io.github.lukeeey.cloudforms</groupId>
    <artifactId>cloudforms</artifactId>
    <version>~SNAPSHOT</version>
</dependency>
```