package inf112.skeleton.app.GUI.player;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;
import com.badlogic.gdx.scenes.scene2d.actions.ParallelAction;
import com.badlogic.gdx.scenes.scene2d.actions.RunnableAction;
import inf112.skeleton.app.GUI.board.SoundPlayer;
import inf112.skeleton.app.GUI.pieces.GUIRobot;
import inf112.skeleton.app.gameLogic.enums.ActionType;
import inf112.skeleton.app.gameLogic.enums.Direction;

public class MovableGUIRobot extends GUIRobot {

    TextureAtlas textureAtlas;
    Sprite newSprite;
    Direction robotFacingDir;

    public MovableGUIRobot(int robotnr) {

        super(robotnr);
//       this.setScale(0.8f);
        setBounds(getX(), getY(), getWidth(), getHeight());
        this.robotFacingDir = Direction.SOUTH;


        textureAtlas = new TextureAtlas("bots/yellowBot/yellow_bot_sprites.txt");

        /*addListener(new DragListener() {
            public void drag(InputEvent event, float x, float y, int pointer) {
                moveBy(x - getWidth() / 2, y - getHeight() / 2);
            }
        });*/

        /*addListener(new InputListener() {
            @Override
            public boolean keyDown(InputEvent event, int keycode) {

                switch (keycode) {
                    case Input.Keys.RIGHT:
                        fullAction(Action.MOVE_1, Direction.EAST);
                        break;
                    case Input.Keys.LEFT:
                        fullAction(Action.MOVE_1, Direction.WEST);
                        break;
                    case Input.Keys.DOWN:
                        fullAction(Action.MOVE_1, Direction.SOUTH);
                        break;
                    case Input.Keys.UP:
                        fullAction(Action.MOVE_1, Direction.NORTH);
                        break;
                    case Input.Keys.R:
                        fullAction(Action.ROTATE_R, Direction.NORTH);
                        break;
                    case Input.Keys.L:
                        fullAction(Action.ROTATE_L, Direction.NORTH);
                        break;
                    case Input.Keys.U:
                        fullAction(Action.ROTATE_U, Direction.NORTH);
                        break;
                }

                return true;
            }
        });*/

    }

        /*public void fullAction(Action action, Direction dir) {
            int numTimes = action.getValue();
            List<com.badlogic.gdx.scenes.scene2d.Action> toDoActions = new ArrayList<com.badlogic.gdx.scenes.scene2d.Action>();
            System.out.println(numTimes);

            for (int i = 0; i < numTimes; i++) {
            //    toDoActions.add(
            //            new SequenceAction(getGUIAction(action.getActionType(), dir)
            //                    , new DelayAction(1)));
                toDoActions.add(getGUIAction(action.getActionType(), dir));
                toDoActions.add(new DelayAction(1));
            }

            SequenceAction sequenceAction = new SequenceAction();

            for(com.badlogic.gdx.scenes.scene2d.Action currAction : toDoActions){
                sequenceAction.addAction(currAction);
            }

            addAction(sequenceAction);


        }*/

    public com.badlogic.gdx.scenes.scene2d.Action getGUIAction(ActionType actionType, final Direction faceDir) {
        setOrigin(getWidth() / 2, getHeight() / 2);

        ParallelAction parallelAction = new ParallelAction();

        switch (actionType) {
            case MOVE:

                parallelAction.addAction(new RunnableAction() {
                    @Override
                    public void run() {
                        SoundPlayer.GameSound.MOVE.playSound();
                    }
                });

                // The move audio
                MoveByAction moveAction = new MoveByAction();
                moveAction.setDuration(0.3f);
                moveAction.setInterpolation(Interpolation.pow3);

                switch (faceDir) {
                    case NORTH:
                        moveAction.setAmount(0f, getHeight());
                        break;
                    case EAST:
                        moveAction.setAmount(getWidth(), 0f);
                        break;
                    case WEST:
                        moveAction.setAmount(-getWidth(), 0);
                        break;
                    case SOUTH:
                        moveAction.setAmount(0f, -getHeight());
                        break;

                }

                parallelAction.addAction(moveAction);
                return parallelAction;

            case ROTATE:


                parallelAction.addAction(new RunnableAction() {
                    @Override
                    public void run() {
                        SoundPlayer.GameSound.ROTATE.playSound();
                    }
                });

                final Direction innerDir = faceDir;

                parallelAction.addAction(new RunnableAction() {
                    @Override
                    public void run() {
                        changeSprite(innerDir);
                    }
                });

                return parallelAction;

        }
        return null;
    }

    public void changeSprite(Direction facingDir) {
        String spriteString = "yellow_robot_";
        switch (facingDir) {
            case NORTH:
                spriteString += "back";
                break;
            case EAST:
                spriteString += "right";
                break;
            case WEST:
                spriteString += "left";
                break;
            case SOUTH:
                spriteString += "front";
                break;
        }


        super.sprite = textureAtlas.createSprite(spriteString);

    }

}
