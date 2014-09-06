///////////////////////////////////////////////////////////////////////////////
// 式神AI

AI_List = new Array();

// 初期化時に呼ばれる
function OnInit() {
    AI_List[0] = new AI_WatchClosest(GetClassObj("net.minecraft.entity.player.EntityPlayer"), 6);
}

// Tick毎に呼ばれる
function OnUpdate() {
    for(var i = 0; i < AI_List.length; i++) {
        AI_List[i].OnUpdate();
    }
}

///////////////////////////////////////////////////////////////////////////////
// 近くのEntityを見るAI

function AI_WatchClosest(classTarget, fDistance) {
    this.classTarget = classTarget;
    this.fDistance = fDistance;
    this.bEnable = false;
    this.target = null;
    this.lookTime = 0;
}

AI_WatchClosest.prototype.OnUpdate = function() {
    // 開始条件
    if(this.bEnable == false) {
        if(theShikigami.getRNG().nextFloat() < 0.04) {
            this.target = theWorld.findNearestEntityWithinAABB(
                            this.classTarget, theShikigami.boundingBox.expand(this.fDistance, 3.0, this.fDistance), theShikigami);
            if(this.target != null) {
                this.lookTime = 40 + theShikigami.getRNG().nextInt(40);
                this.bEnable = true;
            }
        }
    }
    // 終了条件
    if(this.bEnable == true) {
        if(this.target.isEntityAlive() == false) {
            this.bEnable　= false;
        }
        if(this.lookTime < 0) {
            this.bEnable　= false;
        }
    }
    // 実行
    if(this.bEnable == true) {
        theShikigami.getLookHelper().setLookPosition(this.target.posX, this.target.posY + this.target.getEyeHeight(), this.target.posZ,
                     10, theShikigami.getVerticalFaceSpeed());
        this.lookTime--;
    }
}

///////////////////////////////////////////////////////////////////////////////

// 指定した名前のクラスオブジェクトを取得
function GetClassObj(className) {
    return java.lang.Class.forName(className,true,theWorld.getClass().getClassLoader());
}