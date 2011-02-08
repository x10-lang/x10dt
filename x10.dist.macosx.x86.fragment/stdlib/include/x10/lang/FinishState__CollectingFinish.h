#ifndef __X10_LANG_FINISHSTATE__COLLECTINGFINISH_H
#define __X10_LANG_FINISHSTATE__COLLECTINGFINISH_H

#include <x10rt.h>


#define X10_LANG_FINISHSTATE__FINISH_H_NODEPS
#include <x10/lang/FinishState__Finish.h>
#undef X10_LANG_FINISHSTATE__FINISH_H_NODEPS
#define X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
#include <x10/lang/FinishState__CollectingFinishState.h>
#undef X10_LANG_FINISHSTATE__COLLECTINGFINISHSTATE_H_NODEPS
#define X10_IO_CUSTOMSERIALIZATION_H_NODEPS
#include <x10/io/CustomSerialization.h>
#undef X10_IO_CUSTOMSERIALIZATION_H_NODEPS
namespace x10 { namespace lang { 
template<class FMGL(T)> class Reducible;
} } 
namespace x10 { namespace lang { 
class FinishState__RootFinish;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__RootCollectingFinish;
} } 
namespace x10 { namespace io { 
class SerialData;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class GlobalRef;
} } 
#include <x10/lang/GlobalRef.struct_h>
namespace x10 { namespace lang { 
class FinishState;
} } 
namespace x10 { namespace lang { 
class Runtime;
} } 
namespace x10 { namespace lang { 
template<class FMGL(Z1), class FMGL(U)> class Fun_0_1;
} } 
namespace x10 { namespace lang { 
class ClassCastException;
} } 
namespace x10 { namespace lang { 
template<class FMGL(U)> class Fun_0_0;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__RemoteCollectingFinish;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__RemoteCollectingFinish;
} } 
namespace x10 { namespace lang { 
template<class FMGL(T)> class FinishState__RemoteCollectingFinish;
} } 
namespace x10 { namespace lang { 
class Int;
} } 
#include <x10/lang/Int.struct_h>
namespace x10 { namespace lang { 

template<class FMGL(T)> class FinishState__CollectingFinish;
template <> class FinishState__CollectingFinish<void>;
template<class FMGL(T)> class FinishState__CollectingFinish : public x10::lang::FinishState__Finish
  {
    public:
    RTT_H_DECLS_CLASS
    
    static x10aux::itable_entry _itables[4];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    static x10::io::CustomSerialization::itable<x10::lang::FinishState__CollectingFinish<FMGL(T)> > _itable_0;
    
    static x10::lang::Any::itable<x10::lang::FinishState__CollectingFinish<FMGL(T)> > _itable_1;
    
    static typename x10::lang::FinishState__CollectingFinishState<FMGL(T)>::template itable<x10::lang::FinishState__CollectingFinish<FMGL(T)> > _itable_2;
    
    void _instance_init();
    
    x10aux::ref<x10::lang::Reducible<FMGL(T)> > FMGL(reducer);
    
    void _constructor(x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer);
    
    static x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > _make(
             x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer);
    
    void _constructor(x10aux::ref<x10::io::SerialData> data);
    
    static x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > _make(
             x10aux::ref<x10::io::SerialData> data);
    
    virtual x10aux::ref<x10::io::SerialData> serialize(
      );
    virtual void accept(FMGL(T) t, x10_int id);
    virtual FMGL(T) waitForFinishExpr();
    virtual x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >
      x10__lang__FinishState__CollectingFinish____x10__lang__FinishState__CollectingFinish__this(
      );
    
    // Serialization
    public: static const x10aux::serialization_id_t _serialization_id;
    
    public: virtual x10aux::serialization_id_t _get_serialization_id() {
         return _serialization_id;
    }
    
    public: virtual void _serialize_body(x10aux::serialization_buffer& buf);
    
    public: template<class __T> static x10aux::ref<__T> _deserializer(x10aux::deserialization_buffer& buf);
    
    public: void _deserialize_body(x10aux::deserialization_buffer& buf);
    
};
template <> class FinishState__CollectingFinish<void> : public x10::lang::FinishState__Finish
{
    public:
    static x10aux::RuntimeType rtt;
    static const x10aux::RuntimeType* getRTT() { return & rtt; }
    
};

} } 
#endif // X10_LANG_FINISHSTATE__COLLECTINGFINISH_H

namespace x10 { namespace lang { 
template<class FMGL(T)>
class FinishState__CollectingFinish;
} } 

#ifndef X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_NODEPS
#define X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_NODEPS
#include <x10/lang/FinishState__Finish.h>
#include <x10/lang/FinishState__CollectingFinishState.h>
#include <x10/io/CustomSerialization.h>
#include <x10/lang/Reducible.h>
#include <x10/lang/FinishState__RootFinish.h>
#include <x10/lang/FinishState__RootCollectingFinish.h>
#include <x10/io/SerialData.h>
#include <x10/lang/GlobalRef.h>
#include <x10/lang/FinishState.h>
#include <x10/lang/Runtime.h>
#include <x10/lang/Fun_0_1.h>
#include <x10/lang/ClassCastException.h>
#include <x10/lang/Fun_0_0.h>
#include <x10/lang/FinishState__RemoteCollectingFinish.h>
#include <x10/lang/FinishState__RemoteCollectingFinish.h>
#include <x10/lang/FinishState__RemoteCollectingFinish.h>
#include <x10/lang/Int.h>
#ifndef X10_LANG_FINISHSTATE__COLLECTINGFINISH__CLOSURE__0_CLOSURE
#define X10_LANG_FINISHSTATE__COLLECTINGFINISH__CLOSURE__0_CLOSURE
#include <x10/lang/Closure.h>
#include <x10/lang/Fun_0_0.h>
template<class FMGL(T)> class x10_lang_FinishState__CollectingFinish__closure__0 : public x10::lang::Closure {
    public:
    
    static typename x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > >::template itable <x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) > > _itable;
    static x10aux::itable_entry _itables[2];
    
    virtual x10aux::itable_entry* _getITables() { return _itables; }
    
    // closure body
    x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > __apply() {
        
        //#line 594 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
        return x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)>::_make(_ref,
                                                                              tmpReducer);
        
    }
    
    // captured environment
    x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > _ref;
    x10aux::ref<x10::lang::Reducible<FMGL(T)> > tmpReducer;
    
    x10aux::serialization_id_t _get_serialization_id() {
        return _serialization_id;
    }
    
    void _serialize_body(x10aux::serialization_buffer &buf) {
        buf.write(this->_ref);
        buf.write(this->tmpReducer);
    }
    
    template<class __T> static x10aux::ref<__T> _deserialize(x10aux::deserialization_buffer &buf) {
        x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >* storage = x10aux::alloc<x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) > >();
        buf.record_reference(x10aux::ref<x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) > >(storage));
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > that__ref = buf.read<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >();
        x10aux::ref<x10::lang::Reducible<FMGL(T)> > that_tmpReducer = buf.read<x10aux::ref<x10::lang::Reducible<FMGL(T)> > >();
        x10aux::ref<x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) > > this_ = new (storage) x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >(that__ref, that_tmpReducer);
        return this_;
    }
    
    x10_lang_FinishState__CollectingFinish__closure__0(x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > _ref, x10aux::ref<x10::lang::Reducible<FMGL(T)> > tmpReducer) : _ref(_ref), tmpReducer(tmpReducer) { }
    
    static const x10aux::serialization_id_t _serialization_id;
    
    static const x10aux::RuntimeType* getRTT() { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > > >(); }
    virtual const x10aux::RuntimeType *_type() const { return x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > > >(); }
    
    x10aux::ref<x10::lang::String> toString() {
        return x10aux::string_utils::lit(this->toNativeString());
    }
    
    const char* toNativeString() {
        return "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10:594";
    }

};

template<class FMGL(T)> typename x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > >::template itable <x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) > >x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >::_itable(&x10::lang::Reference::equals, &x10::lang::Closure::hashCode, &x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >::__apply, &x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >::toString, &x10::lang::Closure::typeName);template<class FMGL(T)>
x10aux::itable_entry x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >::_itables[2] = {x10aux::itable_entry(&x10aux::getRTT<x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > > >, &x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >::_itable),x10aux::itable_entry(NULL, NULL)};

template<class FMGL(T)>
const x10aux::serialization_id_t x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) >::template _deserialize<x10::lang::Reference>,x10aux::CLOSURE_KIND_NOT_ASYNC);

#endif // X10_LANG_FINISHSTATE__COLLECTINGFINISH__CLOSURE__0_CLOSURE
#ifndef X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_GENERICS
#define X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_GENERICS
template<class FMGL(T)> template<class __T> x10aux::ref<__T> x10::lang::FinishState__CollectingFinish<FMGL(T)>::_deserializer(x10aux::deserialization_buffer& buf) {
    x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__CollectingFinish<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__CollectingFinish<FMGL(T)>))) x10::lang::FinishState__CollectingFinish<FMGL(T)>();
    buf.record_reference(this_);
    this_->_deserialize_body(buf);
    return this_;
}

#endif // X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_GENERICS
#ifndef X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_IMPLEMENTATION
#define X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_IMPLEMENTATION
#include <x10/lang/FinishState__CollectingFinish.h>


template<class FMGL(T)> x10::io::CustomSerialization::itable<x10::lang::FinishState__CollectingFinish<FMGL(T)> >  x10::lang::FinishState__CollectingFinish<FMGL(T)>::_itable_0(&x10::lang::FinishState__CollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::serialize, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> x10::lang::Any::itable<x10::lang::FinishState__CollectingFinish<FMGL(T)> >  x10::lang::FinishState__CollectingFinish<FMGL(T)>::_itable_1(&x10::lang::FinishState__CollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> typename x10::lang::FinishState__CollectingFinishState<FMGL(T)>::template itable<x10::lang::FinishState__CollectingFinish<FMGL(T)> >  x10::lang::FinishState__CollectingFinish<FMGL(T)>::_itable_2(&x10::lang::FinishState__CollectingFinish<FMGL(T)>::accept, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::equals, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::hashCode, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::toString, &x10::lang::FinishState__CollectingFinish<FMGL(T)>::typeName);
template<class FMGL(T)> x10aux::itable_entry x10::lang::FinishState__CollectingFinish<FMGL(T)>::_itables[4] = {x10aux::itable_entry(&x10aux::getRTT<x10::io::CustomSerialization>, &_itable_0), x10aux::itable_entry(&x10aux::getRTT<x10::lang::Any>, &_itable_1), x10aux::itable_entry(&x10aux::getRTT<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >, &_itable_2), x10aux::itable_entry(NULL, (void*)x10aux::getRTT<x10::lang::FinishState__CollectingFinish<FMGL(T)> >())};
template<class FMGL(T)> void x10::lang::FinishState__CollectingFinish<FMGL(T)>::_instance_init() {
    _I_("Doing initialisation for class: x10::lang::FinishState__CollectingFinish<FMGL(T)>");
    
}


//#line 581 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10FieldDecl_c

//#line 582 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::FinishState__CollectingFinish<FMGL(T)>::_constructor(
                          x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer)
{
    this->::x10::lang::FinishState__Finish::_constructor(
      x10aux::class_cast_unchecked<x10aux::ref<x10::lang::FinishState__RootFinish> >(x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::_make(reducer)));
    {
     
    }
    
    //#line 584 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
      FMGL(reducer) =
      reducer;
    
}
template<class FMGL(T)>
x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > x10::lang::FinishState__CollectingFinish<FMGL(T)>::_make(
  x10aux::ref<x10::lang::Reducible<FMGL(T)> > reducer)
{
    x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__CollectingFinish<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__CollectingFinish<FMGL(T)>))) x10::lang::FinishState__CollectingFinish<FMGL(T)>();
    this_->_constructor(reducer);
    return this_;
}



//#line 586 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10ConstructorDecl_c
template<class FMGL(T)> void x10::lang::FinishState__CollectingFinish<FMGL(T)>::_constructor(
                          x10aux::ref<x10::io::SerialData> data)
{
    this->::x10::lang::FinishState__Finish::_constructor(
      x10aux::class_cast<x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > >(x10aux::nullCheck(x10aux::nullCheck(data)->
                                                                                                          FMGL(superclassData))->
                                                                                        FMGL(data)));
    {
     
    }
    
    //#line 588 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
    x10aux::ref<x10::lang::Reducible<FMGL(T)> > tmpReducer =
      x10aux::class_cast<x10aux::ref<x10::lang::Reducible<FMGL(T)> > >(x10aux::nullCheck(data)->
                                                                         FMGL(data));
    
    //#line 589 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
      FMGL(reducer) =
      tmpReducer;
    
    //#line 590 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
    if ((x10aux::struct_equals(x10::lang::Place_methods::place((((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
                                                                  FMGL(ref))->location)->
                                 FMGL(id),
                               x10aux::here)))
    {
        
        //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
          FMGL(me) =
          ((__extension__ ({
            x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > __desugarer__var__71__ =
              ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
                FMGL(ref);
            
            //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
            x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > __var1259__;
            
            //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Labeled_c
            goto __ret2333; __ret2333: 
            //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Do_c
            do
            {
            {
                
                //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10If_c
                if (!((x10aux::struct_equals(x10::lang::Place_methods::place((__desugarer__var__71__)->location),
                                             x10::lang::Place_methods::_make(x10aux::here)))))
                {
                    
                    //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Throw_c
                    x10aux::throwException(x10aux::nullCheck(x10::lang::ClassCastException::_make(x10aux::string_utils::lit("x10.lang.GlobalRef[x10.lang.FinishState]{self.home==here}"))));
                }
                
                //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
                __var1259__ =
                  __desugarer__var__71__;
                
                //#line 591 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Branch_c
                goto __ret2333_end_;
            }
            goto __ret2333_next_; __ret2333_next_: ;
            }
            while (false);
            goto __ret2333_end_; __ret2333_end_: ;
            __var1259__;
        }))
        )->__apply();
    }
    else
    {
        
        //#line 593 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10LocalDecl_c
        x10::lang::GlobalRef<x10aux::ref<x10::lang::FinishState> > _ref =
          ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
            FMGL(ref);
        
        //#line 594 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
        ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
          FMGL(me) =
          x10aux::nullCheck(x10aux::nullCheck(x10::lang::PlaceLocalHandle_methods<x10aux::ref<x10::lang::Runtime> >::__apply(x10::lang::Runtime::
                                                                                                                               FMGL(runtime__get)()))->
                              FMGL(finishStates))->__apply(
            ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
              FMGL(ref),
            x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState> > > >(x10aux::ref<x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > > >(x10aux::ref<x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T) > >(new (x10aux::alloc<x10::lang::Fun_0_0<x10aux::ref<x10::lang::FinishState__RemoteCollectingFinish<FMGL(T)> > > >(sizeof(x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T)>)))x10_lang_FinishState__CollectingFinish__closure__0<FMGL(T)>(_ref, tmpReducer)))));
    }
    
}
template<class FMGL(T)>
x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > x10::lang::FinishState__CollectingFinish<FMGL(T)>::_make(
  x10aux::ref<x10::io::SerialData> data)
{
    x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> > this_ = new (memset(x10aux::alloc<x10::lang::FinishState__CollectingFinish<FMGL(T)> >(), 0, sizeof(x10::lang::FinishState__CollectingFinish<FMGL(T)>))) x10::lang::FinishState__CollectingFinish<FMGL(T)>();
    this_->_constructor(data);
    return this_;
}



//#line 597 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::io::SerialData>
  x10::lang::FinishState__CollectingFinish<FMGL(T)>::serialize(
  ) {
    
    //#line 597 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return x10::io::SerialData::_make(x10aux::class_cast_unchecked<x10aux::ref<x10::lang::Any> >(((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
                                                                                                   FMGL(reducer)),
                                      x10::lang::FinishState__Finish::serialize());
    
}

//#line 598 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> void x10::lang::FinishState__CollectingFinish<FMGL(T)>::accept(
  FMGL(T) t,
  x10_int id) {
    
    //#line 598 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": polyglot.ast.Eval_c
    x10::lang::FinishState__CollectingFinishState<FMGL(T)>::accept(x10aux::nullCheck(x10aux::class_cast<x10aux::ref<x10::lang::FinishState__CollectingFinishState<FMGL(T)> > >(((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
                                                                                                                                                                                 FMGL(me))), 
      t,
      id);
}

//#line 599 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> FMGL(T) x10::lang::FinishState__CollectingFinish<FMGL(T)>::waitForFinishExpr(
  ) {
    
    //#line 599 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return x10aux::nullCheck((x10aux::class_cast<x10aux::ref<x10::lang::FinishState__RootCollectingFinish<FMGL(T)> > >(((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this)->
                                                                                                                         FMGL(me))))->x10::lang::FinishState__RootCollectingFinish<FMGL(T)>::waitForFinishExpr();
    
}

//#line 580 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10MethodDecl_c
template<class FMGL(T)> x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >
  x10::lang::FinishState__CollectingFinish<FMGL(T)>::x10__lang__FinishState__CollectingFinish____x10__lang__FinishState__CollectingFinish__this(
  ) {
    
    //#line 580 "/Users/rfuhrer/eclipse/workspaces/x10dt-trunk/x10.runtime/src-x10/x10/lang/FinishState.x10": x10.ast.X10Return_c
    return ((x10aux::ref<x10::lang::FinishState__CollectingFinish<FMGL(T)> >)this);
    
}
template<class FMGL(T)> const x10aux::serialization_id_t x10::lang::FinishState__CollectingFinish<FMGL(T)>::_serialization_id = 
    x10aux::DeserializationDispatcher::addDeserializer(x10::lang::FinishState__CollectingFinish<FMGL(T)>::template _deserializer<x10::lang::Reference>, x10aux::CLOSURE_KIND_NOT_ASYNC);

template<class FMGL(T)> void x10::lang::FinishState__CollectingFinish<FMGL(T)>::_serialize_body(x10aux::serialization_buffer& buf) {
    /* NOTE: Implements x10.io.CustomSerialization */
    buf.write(this->serialize());
    
}

template<class FMGL(T)> void x10::lang::FinishState__CollectingFinish<FMGL(T)>::_deserialize_body(x10aux::deserialization_buffer& buf) {
    /* NOTE: Implements x10.io.CustomSerialization */
    x10aux::ref<x10::io::SerialData>val_ = buf.read<x10aux::ref<x10::io::SerialData> >();
    _constructor(val_);
    
}

template<class FMGL(T)> x10aux::RuntimeType x10::lang::FinishState__CollectingFinish<FMGL(T)>::rtt;
template<class FMGL(T)> void x10::lang::FinishState__CollectingFinish<FMGL(T)>::_initRTT() {
    const x10aux::RuntimeType *canonical = x10aux::getRTT<x10::lang::FinishState__CollectingFinish<void> >();
    if (rtt.initStageOne(canonical)) return;
    const x10aux::RuntimeType* parents[3] = { x10aux::getRTT<x10::lang::FinishState__Finish>(), x10aux::getRTT<x10::lang::FinishState__CollectingFinishState<FMGL(T)> >(), x10aux::getRTT<x10::io::CustomSerialization>()};
    const x10aux::RuntimeType* params[1] = { x10aux::getRTT<FMGL(T)>()};
    x10aux::RuntimeType::Variance variances[1] = { x10aux::RuntimeType::invariant};
    const char *baseName = "x10.lang.FinishState.CollectingFinish";
    rtt.initStageTwo(baseName, x10aux::RuntimeType::class_kind, 3, parents, 1, params, variances);
}
#endif // X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_IMPLEMENTATION
#endif // __X10_LANG_FINISHSTATE__COLLECTINGFINISH_H_NODEPS
